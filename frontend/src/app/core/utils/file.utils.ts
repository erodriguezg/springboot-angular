import {ArchivoB64Dto} from '../dto/archivo-b64.dto';

const b64toBlob = require('b64-to-blob');
const fileSaver = require('file-saver');

export class FileUtils {

    public static readonly PDF_CONTENT_TYPE = 'application/pdf';
    public static readonly PDF_EXT = 'pdf';
    public static readonly JPEG_CONTENT_TYPE = 'image/jpeg';
    public static readonly JPEG_EXT = 'jpeg';
    public static readonly JPG_CONTENT_TYPE = 'image/jpg';
    public static readonly JPG_EXT = 'jpg';
    public static readonly GIF_CONTENT_TYPE = 'image/gif';
    public static readonly GIF_EXT = 'gif';
    public static readonly PNG_CONTENT_TYPE = 'image/png';
    public static readonly PNG_EXT = 'png';
    public static readonly OCTETSTREAM_CONTENT_TYPE = 'application/octet-stream';


    public static descargarDesdeArchivoB64(archivoB64Dto: ArchivoB64Dto) {
        const fileName = archivoB64Dto.fileName;
        const datab64 = archivoB64Dto.dataB64;
        const contentType = archivoB64Dto.contentType;
        FileUtils.descargarB64(datab64, contentType, fileName);
    }

    public static descargarB64(datab64: string, contentType: string, fileName: string): void {
        const blob = b64toBlob(datab64, contentType);
        fileSaver.saveAs(blob, fileName);
    }

    public static fileToArchivoB64(file: File, callback: (archivoB64Out: ArchivoB64Dto) => void): void {
        const myReader: FileReader = new FileReader();
        const archivoOut: ArchivoB64Dto = <ArchivoB64Dto>{};
        archivoOut.fileName = file.name;
        myReader.onloadend = (e) => {
            const re = /^data:(.+)\;base64,(.+)/;
            const reEval = re.exec(myReader.result as string);
            archivoOut.contentType = reEval[1];
            archivoOut.dataB64 = reEval[2];
            callback(archivoOut);
        }
        myReader.readAsDataURL(file);
    }

    public static getContentType(file: File): string {
        const ext = FileUtils.getExtensionFromFileName(file.name);
        if (ext === FileUtils.PDF_EXT) return FileUtils.PDF_CONTENT_TYPE;
        if (ext === FileUtils.GIF_EXT) return FileUtils.GIF_CONTENT_TYPE;
        if (ext === FileUtils.PNG_EXT) return FileUtils.PNG_CONTENT_TYPE;
        if (ext === FileUtils.JPEG_EXT) return FileUtils.JPEG_CONTENT_TYPE;
        if (ext === FileUtils.JPG_EXT) return FileUtils.JPG_CONTENT_TYPE;
        return FileUtils.OCTETSTREAM_CONTENT_TYPE;
    }

    public static getExtensionFromFileName(fileName: string): string {
        const re = /(?:\.([^.]+))?$/;
        return re.exec(fileName)[1];
    }

    public static getExtensionFromContentType(contentType: string): string {
        if (contentType === FileUtils.PDF_CONTENT_TYPE) return FileUtils.PDF_EXT;
        if (contentType === FileUtils.GIF_CONTENT_TYPE) return FileUtils.GIF_EXT;
        if (contentType === FileUtils.PNG_CONTENT_TYPE) return FileUtils.PNG_EXT;
        if (contentType === FileUtils.JPEG_CONTENT_TYPE) return FileUtils.JPEG_EXT;
        if (contentType === FileUtils.JPG_CONTENT_TYPE) return FileUtils.JPG_EXT;
        return '.file';
    }
}

