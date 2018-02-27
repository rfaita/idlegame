import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'simpleNumber'
})
export class NumberPipe implements PipeTransform {

    transform(number: any, args?: any): any {
        if (number != undefined) {
            let abs = Math.abs(number)
            if (abs >= Math.pow(10, 12)) {
                number = (number / Math.pow(10, 12)).toFixed(0) + "T";
            } else if (abs < Math.pow(10, 12) && abs >= Math.pow(10, 9)) {
                number = (number / Math.pow(10, 9)).toFixed(0) + "B";
            } else if (abs < Math.pow(10, 9) && abs >= Math.pow(10, 6)) {
                number = (number / Math.pow(10, 6)).toFixed(0) + "M";
            } else if (abs < Math.pow(10, 6) && abs >= Math.pow(10, 3)) {
                number = (number / Math.pow(10, 3)).toFixed(0) + "K";
            }

        }
        return number;
    }

}