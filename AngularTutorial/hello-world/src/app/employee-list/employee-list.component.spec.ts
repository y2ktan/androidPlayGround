import { TestBed, inject } from '@angular/core/testing';

import { EmployeeListComponent } from './employee-list.component';

describe('a employee-list component', () => {
	let component: EmployeeListComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				EmployeeListComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([EmployeeListComponent], (EmployeeListComponent) => {
		component = EmployeeListComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});