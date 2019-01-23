import { ArchivoB64Dto } from '../dto/archivo-b64.dto';

import b64toBlob from 'b64-to-blob';

import fileSaver from 'file-saver';

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


    public static downloadFileB64Dto(fileB64Dto: ArchivoB64Dto) {
        const fileName = fileB64Dto.fileName;
        const datab64 = fileB64Dto.dataB64;
        const contentType = fileB64Dto.contentType;
        FileUtils.downloadB64(datab64, contentType, fileName);
    }

    public static downloadB64(datab64: string, contentType: string, fileName: string): void {
        const blob = b64toBlob(datab64, contentType);
        fileSaver.saveAs(blob, fileName);
    }

    public static fileToFileB64Dto(file: File, callback: (fileB64Dto: ArchivoB64Dto) => void): void {
        const myReader: FileReader = new FileReader();
        const outFile: ArchivoB64Dto = {} as ArchivoB64Dto;
        outFile.fileName = file.name;
        myReader.onloadend = (e) => {
            const re = /^data:(.+)\;base64,(.+)/;
            const reEval = re.exec(myReader.result as string);
            outFile.contentType = reEval[1];
            outFile.dataB64 = reEval[2];
            callback(outFile);
        };
        myReader.readAsDataURL(file);
    }

    public static getContentType(file: File): string {
        const ext = FileUtils.getExtensionFromFileName(file.name);
        switch (ext) {
            case FileUtils.PDF_EXT:
                return FileUtils.PDF_CONTENT_TYPE;
            case FileUtils.GIF_EXT:
                return FileUtils.GIF_CONTENT_TYPE;
            case FileUtils.PNG_EXT:
                return FileUtils.PNG_CONTENT_TYPE;
            case FileUtils.JPEG_EXT:
            case FileUtils.JPG_EXT:
                return FileUtils.JPG_CONTENT_TYPE;
            default:
                return FileUtils.OCTETSTREAM_CONTENT_TYPE;
        }
    }

    public static getExtensionFromFileName(fileName: string): string {
        const re = /(?:\.([^.]+))?$/;
        return re.exec(fileName)[1];
    }

    public static getExtensionFromContentType(contentType: string): string {
        switch (contentType) {
            case FileUtils.PDF_CONTENT_TYPE:
                return FileUtils.PDF_EXT;
            case FileUtils.GIF_CONTENT_TYPE:
                return FileUtils.GIF_EXT;
            case FileUtils.PNG_CONTENT_TYPE:
                return FileUtils.PNG_EXT;
            case FileUtils.JPEG_CONTENT_TYPE:
            case FileUtils.JPG_CONTENT_TYPE:
                return FileUtils.JPG_EXT;
        }
        return '.file';
    }
}
