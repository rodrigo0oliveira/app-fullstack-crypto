import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultLayoutLoginComponent } from './default-layout-login.component';

describe('DefaultLayoutLoginComponent', () => {
  let component: DefaultLayoutLoginComponent;
  let fixture: ComponentFixture<DefaultLayoutLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefaultLayoutLoginComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefaultLayoutLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
