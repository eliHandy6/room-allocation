import React, { useState ,useEffect} from 'react'
import {
  CBadge,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CDataTable,
  CRow
} from '@coreui/react'
import { DocsLink } from 'src/reusable';
import axios from 'axios';

const BUILDING_API_BASE_URL="http://localhost:9000/apis/v1/buildings";
const fields = ['name','department', 'created_at']
const ViewBuildings = () => {
  const [buildings, setBuildings] = useState({data_list: []});
  useEffect(()=>{
    axios.get(BUILDING_API_BASE_URL)
    .then(res=>{
      console.log(res.data)
      setBuildings(res.data)
       
    })
    .catch(err=>{
     
    })
},[])

  const [activeIndex] = useState(1)

  return (
    <>
    <CRow  className="justify-content-center">
      <CCol xs="12" lg="8">
        <CCard>
          <CCardHeader>
            Buildings 
            <DocsLink name="CModal"/>
          </CCardHeader>
          <CCardBody>
          <CDataTable
            items={buildings.data_list[0]}
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

export default ViewBuildings
