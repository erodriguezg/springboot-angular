export interface Sesion {
    token: string;
    iat: number;
    exp: number;
    jti: string;
    data: any;
}
