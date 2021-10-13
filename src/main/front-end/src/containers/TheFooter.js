import React from 'react'
import { CFooter } from '@coreui/react'

const TheFooter = () => {
  return (
    <CFooter fixed={false}>
      <div>
        <a href="#" target="_blank" rel="noopener noreferrer">Chuka University</a>
        <span className="ml-1">&copy; 2021 Chuka University.</span>
      </div>
      <div className="mfs-auto">
        <span className="mr-1">Designed by</span>
        <a href="#" target="_blank" rel="noopener noreferrer">Apopo Tola</a>
      </div>
    </CFooter>
  )
}

export default React.memo(TheFooter)
