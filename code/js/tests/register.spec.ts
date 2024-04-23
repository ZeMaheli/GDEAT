import {expect, test} from '@playwright/test';
import generateString from './utils'

test('Register page redirects to user page if register is successful', async ({browser}) => {
    const context = await browser.newContext()
    const page = await context.newPage()
    // Navigate to the register page
    await page.goto('http://localhost:9000/gomoku/register');

    // Fill in the register form
    const username = generateString(6);
    const email = generateString(15)
    await page.fill('input[name="email"]', email);
    await page.fill('input[name="password"]', 'example-password');
    await page.fill('input[name="username"]', username);

    await page.click('button[type="submit"]');
    await page.waitForSelector('a#play-config');

    // Assert that the page redirected successfully
    const newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/');

    // Assert that the "Welcome to Gomoku" text is visible after successful login
    const helloText = await page.innerText('div:visible');
    expect(helloText).toContain('Welcome to Gomoku');

    // Assert that cookies are set
    const cookies = await context.cookies();
    const accessTokenCookie = cookies.find(cookie => cookie.name === 'access_token');
    expect(accessTokenCookie).toBeDefined();

    await context.close()
});

test('Register page doesnt redirect, when register is unsuccessful', async ({browser}) => {
    const context = await browser.newContext()
    const page = await context.newPage()
    // Navigate to the login page
    await page.goto('http://localhost:9000/gomoku/register');

    // Fill in the login form
    await page.fill('input[name="email"]', 'email@example.com');
    await page.fill('input[name="username"]', 'example-username');
    await page.fill('input[name="password"]', '1');

    // Click the login button
    await page.click('button[type="submit"]');

    // Assert that the page redirected successfully
    const newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/gomoku/register');

    const errorText = await page.innerText('p[style*="color: red;"]');
    expect(errorText).toContain('Password too weak');

    await context.close()
});

test('When clicking home, redirects to main page', async ({browser}) => {
    const context = await browser.newContext()
    const page = await context.newPage()
    // Navigate to the login page
    await page.goto('http://localhost:9000/gomoku/register');

    // Click the home link
    const homeLink = page.locator('.home-redirect');
    await homeLink.click();

    // Assert that the page redirected to the home page
    const newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/');

    await context.close()
});

test('When clicking login, redirects to login page', async ({browser}) => {
    const context = await browser.newContext()
    const page = await context.newPage()
    // Navigate to the login page
    await page.goto('http://localhost:9000/gomoku/register');

    // Click the home link
    const homeLink = page.locator('#login-redirect');
    await homeLink.click();

    // Assert that the page redirected to the home page
    const newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/gomoku/login');
    await context.close()
});
