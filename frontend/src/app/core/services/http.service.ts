import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable({
    providedIn: 'root'
  })
export class HttpService {

    static readonly CONTENT_TYPE: string = 'Content-Type';
    static readonly APPLICATION_JSON_UTF8: string = 'application/json; charset=utf-8';
    static readonly APPLICATION_X_WWW_FORM_URLENCODED: string = 'application/x-www-form-urlencoded';

    constructor(private readonly http: HttpClient) {
    }

    public postMultipartFormForJson<T>(url: string, formData: FormData | null, blockui = true): Observable<T> {
        const headersAux = new HttpHeaders;
        /* no se especifica el header content-type para que automaticamente lo detecte y lo llene con bondary */
        return this.http.post<T>(url, formData ?  formData : null, {
            headers: headersAux,
            responseType: 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public postFormForJson<T>(url: string, body: URLSearchParams | null, blockui = true): Observable<T> {
        let headersAux = new HttpHeaders();
        headersAux = headersAux.set(HttpService.CONTENT_TYPE, HttpService.APPLICATION_X_WWW_FORM_URLENCODED);
        return this.http.post<T>(url, body ? body.toString() : null, {
            headers: headersAux,
            responseType: 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public postFormForText(url: string, body: URLSearchParams | null, blockui = true): Observable<string> {
        let headersAux = new HttpHeaders();
        headersAux = headersAux.set(HttpService.CONTENT_TYPE, HttpService.APPLICATION_X_WWW_FORM_URLENCODED);
        return this.http.post<string>(url, body ? body.toString() : null, {
            headers: headersAux,
            responseType: 'text' as 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public postFormForBlob(url: string, body: URLSearchParams | null, blockui = true): Observable<Blob> {
        let headersAux = new HttpHeaders();
        headersAux = headersAux.set(HttpService.CONTENT_TYPE, HttpService.APPLICATION_X_WWW_FORM_URLENCODED);
        return this.http.post<Blob>(url, body ? body.toString() : null, {
            headers: headersAux,
            responseType: 'blob' as 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public postJson<T>(url: string, body: any | null, blockui = true): Observable<T> {
        let headersAux = new HttpHeaders();
        headersAux = headersAux.set(HttpService.CONTENT_TYPE, HttpService.APPLICATION_JSON_UTF8);
        return this.http.post<T>(url, body, {
            headers: headersAux,
            responseType: 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public postJsonForText(url: string, body: any | null, blockui = true): Observable<string> {
        let headersAux = new HttpHeaders();
        headersAux = headersAux.set(HttpService.CONTENT_TYPE, HttpService.APPLICATION_JSON_UTF8);
        return this.http.post<string>(url, body, {
            headers: headersAux,
            responseType: 'text' as 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public getJson<T>(url: string, blockui = true): Observable<T> {
        let headersAux = new HttpHeaders();
        headersAux = headersAux.set(HttpService.CONTENT_TYPE, HttpService.APPLICATION_JSON_UTF8);
        return this.http.get<T>(url, {headers: headersAux, responseType: 'json', params: {blockui: blockui.toString()}});
    }

}
