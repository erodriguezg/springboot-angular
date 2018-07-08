export class DateUtils {

    public static parcheFecha12Horas(date: Date) : Date {
        if(!date) {
            return null;
        }
        let salidaDate : Date = new Date(date);
        salidaDate.setHours(12);
        return salidaDate;
    }

    public static createUTC(dateInput : Date) : Date {
        return new Date(Date.UTC(
            dateInput.getFullYear(),
            dateInput.getMonth(),
            dateInput.getDate(),
            dateInput.getHours(),
            dateInput.getMinutes(),
            dateInput.getSeconds(),
            dateInput.getMilliseconds()));
    }

}