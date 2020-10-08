export interface ITarifas {
  id?: number;
  precio?: number;
  juegoNombre?: string;
  juegoId?: number;
}

export class Tarifas implements ITarifas {
  constructor(public id?: number, public precio?: number, public juegoNombre?: string, public juegoId?: number) {}
}
