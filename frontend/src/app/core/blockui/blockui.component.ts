import {Component, OnDestroy} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import { BlockuiService } from '../services/blockui.service';

@Component({
  selector: 'app-blockui',
  templateUrl: './blockui.component.html',
  styleUrls: ['./blockui.component.css']
})
export class BlockuiComponent implements OnDestroy {
  public dialogVisible: boolean;
  private onShowSubcription: Subscription;
  private onHideSubcription: Subscription;

  constructor(private blockuiService: BlockuiService) {
      this.dialogVisible = false;
      this.onShowSubcription = this.blockuiService.onShow
          .subscribe(() => this.dialogVisible = true);
      this.onHideSubcription = this.blockuiService.onHide
          .subscribe(() => this.dialogVisible = false);
  }

  ngOnDestroy(): void {
      if (this.onShowSubcription) {
          this.onShowSubcription.unsubscribe();
      }
      if (this.onHideSubcription) {
          this.onHideSubcription.unsubscribe();
      }
  }
}
