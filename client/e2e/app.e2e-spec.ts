import { DocseaPage } from './app.po';

describe('docsea App', () => {
  let page: DocseaPage;

  beforeEach(() => {
    page = new DocseaPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
