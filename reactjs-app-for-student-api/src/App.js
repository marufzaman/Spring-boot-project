/* eslint-disable no-unused-vars */
import { Divider, Grid, TablePagination, Typography } from '@material-ui/core'
import MaterialTable from 'material-table'
import React, { useEffect, useState } from 'react'
import './App.css'

function App() {
  const url = 'http://localhost:8080/api/v1/students/'

  const [tableData, setTableData] = useState([])
  useEffect(() => {
    getStudents()
  }, [])

  const getStudents = () => {
    fetch(url)
      .then((response) => response.json())
      .then((response) => setTableData(response))
  }

  // const getStudents = apiData.map((profile) => {
  //   return profile
  // })

  const columns = [
    {
      title: 'Name',
      field: 'name',
      validate: (rowData) => {
        if (rowData.name === undefined || rowData.name === '') {
          return 'REQUIRED***'
        } else {
          return true
        }
      },
      searchable: true,
      filterPlaceholder: 'Filter by Name',
    },
    {
      title: 'Date of Birth',
      field: 'dob',
      type: 'date',
      validate: (rowData) => {
        if (rowData.dob === undefined || rowData.dob === null) {
          return 'REQUIRED***'
        } else {
          return true
        }
      },
      searchable: false,
      filterPlaceholder: 'Filter by Birthday',
    },
    {
      title: 'Gender',
      field: 'gender',
      validate: (rowData) => {
        if (rowData.gender === undefined || rowData.gender === '') {
          return 'REQUIRED***'
        } else {
          return true
        }
      },
      lookup: { Male: 'Male', Female: 'Female', Others: 'Others' },
      searchable: false,
      filterPlaceholder: 'Filter by Gender',
      export: false,
    },
    {
      title: 'Note',
      field: 'note',
      emptyValue: () => <em>-----</em>,
      sorting: false,
      searchable: false,
      filtering: false,
    },
  ]

  return (
    <div className="App">
      <h2 align="center">Student Profile Management System</h2>

      <MaterialTable
        title="Student List"
        data={tableData}
        columns={columns}
        components={{
          Pagination: (props) => (
            <div>
              <Grid
                container
                style={{
                  padding: 8,
                  background: '#e5edd0',
                }}
              >
                <Grid sm={6} item align="Left">
                  <Typography variant="subtitle2">Total</Typography>
                </Grid>
                <Grid sm={6} item align="center">
                  <Typography variant="subtitle2">
                    Students Found:{props.count}
                  </Typography>
                </Grid>
              </Grid>
              <Divider />
              <TablePagination {...props} />
            </div>
          ),
        }}
        editable={{
          onRowAdd: (newRow) =>
            new Promise((resolve, reject) => {
              const updatedData = [...tableData, newRow]

              fetch(url, {
                method: 'POST',
                headers: {
                  'Content-type': 'application/json',
                },
                body: JSON.stringify(newRow),
              })
                .then((response) => response.json())
                .then((response) => getStudents())

              setTimeout(() => {
                setTableData(updatedData)
                resolve()
              }, 500)
            }),
          onRowUpdate: (newRow, oldRow) =>
            new Promise((resolve, reject) => {
              const updatedData = [...tableData]
              updatedData[oldRow.tableData.id] = newRow

              fetch(
                url +
                  oldRow.id +
                  '?name=' +
                  newRow.name +
                  '&dob=' +
                  newRow.dob +
                  '&gender=' +
                  newRow.gender +
                  '&note=' +
                  newRow.note,
                {
                  method: 'PUT',
                },
              )
                .then((response) => response.json())
                .then(getStudents())
                .then((response) => console.log(response))
              setTimeout(() => {
                setTableData(updatedData)
                resolve()
              }, 500)
            }),
          onRowDelete: (selectedRow) =>
            new Promise((resolve, reject) => {
              const updatedData = [...tableData]
              updatedData.splice(selectedRow.tableData.id, 1)
              fetch(url + selectedRow.id, {
                method: 'DELETE',
              })
                .then((response) => response.json())
                .then(getStudents())
                .then((response) => console.log(response))
              setTimeout(() => {
                setTableData(updatedData)
                resolve()
              }, 1000)
            }),
        }}
        options={{
          sorting: true,
          search: true,
          searchFieldAlignment: 'right',
          searchAutoFocus: true,
          searchFieldVariant: 'outlined',
          filtering: true,
          paging: true,
          pageSize: 10,
          pageSizeOptions: [
            5,
            10,
            20,
            30,
            50,
            100,
            { value: getStudents.length, label: 'All' },
          ],
          showFirstLastPageButtons: true,
          paginationPosition: 'bottom',
          exportButton: true,
          exportAllData: true,
          exportFileName: 'all-student-list',
          addRowPosition: 'first',
          actionsColumnIndex: -1,

          //

          rowStyle: (data, index) =>
            index % 2 === 0
              ? { background: '#f5f5f5' }
              : { background: 'white' },
          headerStyle: {
            background: '#d5d4d4',
            fontWeight: 'bold',
          },

          //
        }}
      />
    </div>
  )
}

export default App
