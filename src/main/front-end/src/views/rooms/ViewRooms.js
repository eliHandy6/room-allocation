import React, { useState, useEffect } from 'react'
import {
  CBadge,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CDataTable,
  CRow
} from '@coreui/react'
import { DocsLink } from 'src/reusable'
import axios from 'axios';

const ROOM_API_BASE_URL = "http://localhost:9000/apis/v1/rooms";
const fields = ['name', 'building','capacity','room_created_at']

const ViewRooms = () => {
  const [rooms, setRooms] = useState({ data_list: [] });

  useEffect(() => {
    axios.get(ROOM_API_BASE_URL)
      .then(res => {
        console.log(res.data)
        setRooms(res.data)
      })
      .catch(err => {

      })
  }, [])
  const [activeIndex] = useState(1)

  return (
    <>
      <CRow className="justify-content-center">
        <CCol xs="12" lg="8">
          <CCard>
            <CCardHeader>
              Rooms
              <DocsLink name="CModal" />
            </CCardHeader>
            <CCardBody>
              <CDataTable
                items={rooms.data_list[0]}
                fields={fields}
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


    </>
  )
}

export default ViewRooms
