import { PegoEntregoPage } from './app.po';

describe('pego-entrego App', () => {
  let page: PegoEntregoPage;

  beforeEach(() => {
    page = new PegoEntregoPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
