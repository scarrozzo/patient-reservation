import {Component, OnInit} from "@angular/core";
import { CalendarOptions } from '@fullcalendar/angular';
import {VisitService} from "../../services/visit.service";
import {Visit} from "../../model/visit"; // useful for typechecking

@Component({
  selector: 'visits',
  templateUrl: './visits.html',
  styleUrls: ['./visits.scss']
})
export class VisitsComponent implements OnInit {

  constructor(private visitService: VisitService) {
  }

  ngOnInit(): void {
    this.loadVisits();
  }

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    dateClick: this.handleDateClick.bind(this),
    events: [
      { title: 'event 1', date: '2022-12-12' },
      { title: 'event 2', date: '2022-12-13' }
    ]
  };

  handleDateClick(){
    console.log("date clicked");
  }

  loadVisits(){
    let events: any[];
    let startDate = new Date();
    startDate.setDate(startDate.getDate() - 30);
    this.visitService.getVisits(startDate.toISOString(), 0, 200).subscribe((res: any) => {
      console.log(res._embedded);

      if(!!res._embedded && !!res._embedded.visits){
        let visits : Visit[];
        visits = res._embedded.visits;
        events = visits.map(
          visit => {
            return { date: visit.date, title: visit.visitType + " - " + visit.patientFirstName + " " + visit.patientLastName }
          });

        this.calendarOptions.events = events;
      }
    });
  }
}
