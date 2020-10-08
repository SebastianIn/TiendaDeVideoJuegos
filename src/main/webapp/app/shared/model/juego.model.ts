import { Moment } from 'moment';

export interface IJuego {
  id?: number;
  nombre?: string;
  fechaPublicacion?: Moment;
  protagonistas?: string;
  director?: string;
  productor?: string;
  tecnologia?: string;
}

export class Juego implements IJuego {
  constructor(
    public id?: number,
    public nombre?: string,
    public fechaPublicacion?: Moment,
    public protagonistas?: string,
    public director?: string,
    public productor?: string,
    public tecnologia?: string
  ) {}
}
