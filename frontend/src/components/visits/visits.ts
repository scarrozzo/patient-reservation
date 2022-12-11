import {Component, OnInit} from "@angular/core";
import { CalendarOptions } from '@fullcalendar/angular'; // useful for typechecking

@Component({
  selector: 'visits',
  templateUrl: './visits.html',
  styleUrls: ['./visits.scss']
})
export class VisitsComponent implements OnInit {
  ngOnInit(): void {
  }

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth'
  };

}
