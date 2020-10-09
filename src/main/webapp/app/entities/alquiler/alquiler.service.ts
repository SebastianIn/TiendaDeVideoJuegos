import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAlquiler } from 'app/shared/model/alquiler.model';

type EntityResponseType = HttpResponse<IAlquiler>;
type EntityArrayResponseType = HttpResponse<IAlquiler[]>;

@Injectable({ providedIn: 'root' })
export class AlquilerService {
  public resourceUrl = SERVER_API_URL + 'api/alquilers';

  constructor(protected http: HttpClient) {}

  create(alquiler: IAlquiler): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alquiler);
    return this.http
      .post<IAlquiler>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(alquiler: IAlquiler): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alquiler);
    return this.http
      .put<IAlquiler>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAlquiler>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAlquiler[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(alquiler: IAlquiler): IAlquiler {
    const copy: IAlquiler = Object.assign({}, alquiler, {
      fecha: alquiler.fecha && alquiler.fecha.isValid() ? alquiler.fecha.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fecha = res.body.fecha ? moment(res.body.fecha) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((alquiler: IAlquiler) => {
        alquiler.fecha = alquiler.fecha ? moment(alquiler.fecha) : undefined;
      });
    }
    return res;
  }
}
