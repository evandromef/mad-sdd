import { render } from '@testing-library/angular';
import { provideRouter } from '@angular/router';
import { App } from './app';

describe('App', () => {
  it('should create the app', async () => {
    const { fixture } = await render(App, {
      providers: [provideRouter([])]
    });

    const appInstance = fixture.componentInstance;

    expect(appInstance).toBeInstanceOf(App);
  });
});
