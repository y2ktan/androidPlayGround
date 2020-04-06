import { TestBed, inject } from '@angular/core/testing';

import { DepartmentListComponent } from './department-list.component';

describe('a department-list component', () => {
	let component: DepartmentListComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				DepartmentListComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([DepartmentListComponent], (DepartmentListComponent) => {
		component = DepartmentListComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});