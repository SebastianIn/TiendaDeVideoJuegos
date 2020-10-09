export interface IInventario {
  id?: number;
  total?: number;
  disponibles?: number;
  enAlquiler?: number;
  juegoNombre?: string;
  juegoId?: number;
}

export class Inventario implements IInventario {
  constructor(
    public id?: number,
    public total?: number,
    public disponibles?: number,
    public enAlquiler?: number,
    public juegoNombre?: string,
    public juegoId?: number
  ) {}
}
