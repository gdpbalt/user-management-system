import { Time } from "@angular/common"

export interface User {
  id : number
  name : string
  firstName: string
  lastName: string
  roleIds : number[]
  status : string
  createdAt : Date
}
