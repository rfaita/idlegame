import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timeTo'
})
export class TimeToPipe implements PipeTransform {

  transform(timeTo: any, args?: any): any {
    timeTo = timeTo || 0;
    timeTo /= 1000;

    let hours = Math.floor(timeTo / 3600);
    hours = hours > 0 ? hours : 0;
    timeTo %= 3600;
    let minutes = Math.floor(timeTo / 60);
    minutes = minutes > 0 ? minutes : 0;
    timeTo %= 60;
    let seconds = Math.floor(timeTo);
    seconds = seconds > 0 ? seconds : 0;

    return (hours >= 10 ? hours : "0" + hours) + ":" + (minutes >= 10 ? minutes : "0" + minutes) + ":" + (seconds >= 10 ? seconds : "0" + seconds);
  }

}
