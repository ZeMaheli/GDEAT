import {expect, test} from '@playwright/test';
import generateString from "./utils";

test('Matchmake redirects to lobby', async ({browser}) => {

    const context = await browser.newContext()
    const page = await context.newPage()
    //Create first user
    await page.goto('http://localhost:9000/gomoku/register');

    const username = generateString(6);
    const email = generateString(15)

    // Fill in the register form
    await page.fill('input[name="email"]', email);
    await page.fill('input[name="password"]', 'example2-password');
    await page.fill('input[name="username"]', username);

    await page.click('button[type="submit"]');
    await page.waitForSelector('a#play-config');

    //Created first user, including cookies in browser

    let newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/');

    // Assert that cookies are set
    const cookies = await context.cookies();
    const accessTokenCookie = cookies.find(cookie => cookie.name === 'access_token');
    expect(accessTokenCookie).toBeDefined();

    await page.click('a#play-config');
    await page.waitForSelector('#play-game');

    //In config page
    newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/gomoku/configuration');

    // Fill in form inputs if necessary
    await page.selectOption('#game-variation', 'normal');
    await page.fill('#game-size', '15');

    // Click the play button
    await page.click('#play-game');

    // Assert that the URL matches the expected redirect URL
    expect(page.url()).toContain('http://localhost:9000/gomoku/matchmaking?boardSize=15&variant=normal');

    await context.close()
});

test('Matchmake with 2 users will redirect to game after polling', async ({browser}) => {

    const context1 = await browser.newContext()
    const page1 = await context1.newPage()

    const context2 = await browser.newContext()
    const page2 = await context2.newPage()

    //Create first user
    await page1.goto('http://localhost:9000/gomoku/register');

    const username1 = generateString(6);
    const email1 = generateString(15)

    // Fill in the register form
    await page1.fill('input[name="email"]', email1);
    await page1.fill('input[name="password"]', 'example2-password');
    await page1.fill('input[name="username"]', username1);

    await page1.click('button[type="submit"]');
    await page1.waitForSelector('a#play-config');
    //Created first user, including cookies in browser

    //Create second user
    await page2.goto('http://localhost:9000/gomoku/register');

    const username2 = generateString(6);
    const email2 = generateString(15)

    await page2.fill('input[name="email"]', email2);
    await page2.fill('input[name="password"]', 'example2-password');
    await page2.fill('input[name="username"]', username2);

    await page2.click('button[type="submit"]');
    await page2.waitForSelector('a#play-config');
    //Created second user, including cookies in browser

    //First user matchmakes
    await page1.click('a#play-config');
    await page1.waitForSelector('#play-game');

    await page1.selectOption('#game-variation', 'normal');
    await page1.fill('#game-size', '15');

    await page1.click('#play-game');

    expect(page1.url()).toContain('http://localhost:9000/gomoku/matchmaking?boardSize=15&variant=normal');
    //First use is inside lobby

    //Second user matchmakes
    await page2.click('a#play-config');
    await page2.waitForSelector('#play-game');

    await page2.selectOption('#game-variation', 'normal');
    await page2.fill('#game-size', '15');

    await page2.click('#play-game');

    expect(page2.url()).toContain('http://localhost:9000/gomoku/matchmaking?boardSize=15&variant=normal');
    //Second use is inside lobby

    await context1.close()
    await context2.close()
});
