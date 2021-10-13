import React, { useState, useEffect } from 'react'
import {
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CDataTable,
} from '@coreui/react'
import { DocsLink } from 'src/reusable'
import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import axios from 'axios'


const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <div>
      <label htmlFor={props.id || props.name}>{label}</label>
      <br />
      <select {...field} {...props} />
      {meta.touched && meta.error ? (
        <div className="error">{meta.error}</div>
      ) : null}
    </div>
  );
};
const BUILDING_API_BASE_URL = "http://localhost:9000/apis/v1/buildings";
const ROOMS_API_BASE_URL = "http://localhost:9000/apis/v1/rooms/buildings";
const fields = ['id', 'name']
const room_fields = ['name', 'building', 'capacity']
const ViewRoomsInABuilding = () => {

  const [rooms, setRooms] = useState({ data_list: [] });
  const [buildingArray, setBuildingArray] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState();

  useEffect(() => {
    axios.get(BUILDING_API_BASE_URL)
      .then(res => {
        console.log(res.data)
        setBuildingArray(res.data.data_list[0])
      })
      .catch(err => {
      })

    axios.get(ROOMS_API_BASE_URL +'/'+selectedRoom)
      .then(res => {
        console.log(res.data)
        setRooms(res.data)
      })
      .catch(err => {

      })
  }, [selectedRoom])

  if (!buildingArray) {
    return <h1>Buildings not found</h1>
  }
  return (
    <CRow>
      <CCol md="6">
        <CCard>
          <CCardHeader>
            Select the Building
            <DocsLink name="CCallout" />
          </CCardHeader>

          <CCardBody>
            <Formik
              initialValues={{
                building_id: ''
              }}
              validationSchema={Yup.object({
                building_id: Yup.string()
                  .max(20, 'Must be 20 characters or less')
                  .required('Required'),
              })}

              onSubmit={(values, { setSubmitting }) => {
                setSelectedRoom(values.building_id)

              }}
            >
              <Form>
                <CRow className="justify-content-center">
                  <CCol md="6">
                    <MySelect label="Building" name="building_id">
                      {buildingArray.map((c) =>
                        <option key={c.id} value={c.id} type="number">{c.name} </option>
                      )}
                    </MySelect>
                  </CCol>
                </CRow>
                <br></br>
                <CRow className="justify-content-center">
                  <CCol md="6">
                    <button type="submit">Submit</button>
                  </CCol>
                </CRow>
              </Form>
            </Formik>
          </CCardBody>
        </CCard>

      </CCol>
      <CCol md={6}>

        <CCard>
          <CCardHeader>
            Rooms
          </CCardHeader>
          <CCardBody>
            <CDataTable
              items={rooms.data_list[0]}
              fields={room_fields}
              itemsPerPage={5}
              pagination
              scopedSlots={{
                'building':
                  (item) => (
                    <td>
                      {item.building.name}

                    </td>
                  )

              }}

            />
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  )
}

export default ViewRoomsInABuilding;
