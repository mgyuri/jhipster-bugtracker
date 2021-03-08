import { ITicket } from 'app/shared/model/ticket.model';

export interface IAttachment {
  id?: number;
  name?: string;
  fileContentType?: string;
  file?: any;
  ticket?: ITicket;
}

export class Attachment implements IAttachment {
  constructor(public id?: number, public name?: string, public fileContentType?: string, public file?: any, public ticket?: ITicket) {}
}
