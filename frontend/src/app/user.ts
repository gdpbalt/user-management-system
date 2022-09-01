import { Time } from "@angular/common"

export interface User {
  id : number;
  name : string;
  firstName: string;
  lastName: string;
  role : string;
  status : string;
  createdAt : Date;
}
