import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class HttpService {

    constructor(private http: HttpClient) {
    }

    public postFormForJson<T>(url: string, body: URLSearchParams | null, blockui: boolean = true): Observable<T> {
        let headers = new HttpHeaders();
        headers = headers.set('Content-Type', 'application/x-www-form-urlencoded');
        return this.http.post<T>(url, body ? body.toString() : null, {
            headers: headers,
            responseType: 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public postJson<T>(url: string, body: any | null, blockui: boolean = true): Observable<T> {
        let headers = new HttpHeaders();
        headers = headers.set('Content-Type', 'application/json; charset=utf-8');
        return this.http.post<T>(url, body, {
            headers: headers,
            responseType: 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public postJsonForText(url: string, body: any | null, blockui: boolean = true): Observable<string> {
        let headers = new HttpHeaders();
        headers = headers.set('Content-Type', 'application/json; charset=utf-8');
        return this.http.post<string>(url, body, {
            headers: headers,
            responseType: 'text' as 'json',
            params: {blockui: blockui.toString()}
        });
    }

    public getJson<T>(url: string, blockui: boolean = true): Observable<T> {
        let headers = new HttpHeaders();
        headers = headers.set('Content-Type', 'application/json; charset=utf-8');
        return this.http.get<T>(url, {headers: headers, responseType: 'json', params: {blockui: blockui.toString()}});
    }

}
