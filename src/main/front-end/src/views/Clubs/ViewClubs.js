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
import { getIconsView } from '../icons/brands/Brands.js'
import { flagSet } from '@coreui/icons'
import { DocsLink } from 'src/reusable'
import axios from 'axios';

const CLUBS_API_BASE_URL = "http://localhost:9000/apis/v1/clubs";
const fields = ['name', 'description','club_email','contact_person','contact_person_phone','created_at']


const ViewClubs = () => {

  const [clubs, setClubs] = useState({ data_list: [] });

  useEffect(() => {
    axios.get(CLUBS_API_BASE_URL)
      .then(res => {
        console.log(res.data)
        setClubs(res.data)
      })
      .catch(err => {

      })
  }, [])
  const [activeIndex] = useState(1)
  return (
    <>
    <CRow className="justify-content-center">
      <CCol xs="12" lg="11">
        <CCard>
          <CCardHeader>
            Clubs
            <DocsLink name="CModal" />
          </CCardHeader>
          <CCardBody>
            <CDataTable
              items={clubs.data_list[0]}
              fields={fields}
              itemsPerPage={5}
              pagination
              

            />
          </CCardBody>
        </CCard>
      </CCol>

    </CRow>


  </>
  )
}

export default ViewClubs
