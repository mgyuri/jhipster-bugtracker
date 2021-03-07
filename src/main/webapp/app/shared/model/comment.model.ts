import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IComment {
  id?: number;
  date?: Moment;
  text?: string;
  parents?: IComment[];
  login?: IUser;
  child?: IComment;
}

export class Comment implements IComment {
  constructor(
    public id?: number,
    public date?: Moment,
    public text?: string,
    public parents?: IComment[],
    public login?: IUser,
    public child?: IComment
  ) {}
}
