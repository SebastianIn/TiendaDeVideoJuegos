import { Moment } from 'moment';
import { IJuego } from 'app/shared/model/juego.model';

export interface IAlquiler {
  id?: number;
  fecha?: Moment;
  monto?: number;
  rangoEdadAlcomprar?: number;
  clienteCedula?: string;
  clienteId?: number;
  juegos?: IJuego[];
}

export class Alquiler implements IAlquiler {
  constructor(
    public id?: number,
    public fecha?: Moment,
    public monto?: number,
    public rangoEdadAlcomprar?: number,
    public clienteCedula?: string,
    public clienteId?: number,
    public juegos?: IJuego[]
  ) {}
}
