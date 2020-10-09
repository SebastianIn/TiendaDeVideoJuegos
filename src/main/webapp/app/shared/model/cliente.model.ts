import { Moment } from 'moment';

export interface ICliente {
  id?: number;
  nombre?: string;
  apellido?: string;
  cedula?: string;
  fechaNacimiento?: Moment;
  rangoEdad?: number;
  telefono?: string;
  correo?: string;
  direccion?: string;
}

export class Cliente implements ICliente {
  constructor(
    public id?: number,
    public nombre?: string,
    public apellido?: string,
    public cedula?: string,
    public fechaNacimiento?: Moment,
    public rangoEdad?: number,
    public telefono?: string,
    public correo?: string,
    public direccion?: string
  ) {}
}
