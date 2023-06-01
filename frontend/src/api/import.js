import axios from 'axios'

const r = axios.create({
  timeout: 200000
})
export function importFile (formData, head) {
  return r({
    url: '/note/categories/uploadNotes',
    method: 'post',
    headers: {
      // 'Content-Type': 'application/pdf'
      // 'Content-Type': 'application/msword'
      'Content-Type': 'multipart/form-data',
      token: localStorage.getItem('token'),
      'Request-DateTime': new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .slice(0, -5)
        .replace('T', ' ')
    },
    data: formData,
    params: {
      categoriesId: head
    }
  })
}
