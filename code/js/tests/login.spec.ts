import { test, expect } from '@playwright/test';

test('Login page doesnt redirect to user page, when user doesnt exist', async ({ browser }) => {
    const context = await browser.newContext()
    const page = await context.newPage()
    // Navigate to the login page
    await page.goto('http://localhost:9000/gomoku/login');

    // Fill in the login form
    await page.fill('input[name="email"]', 'email@example.com');
    await page.fill('input[name="password"]', 'password');

    // Click the login button
    await page.click('button[type="submit"]');

    // Assert that the page redirected successfully
    const newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/gomoku/login');

    const errorText = await page.innerText('p[style*="color: red;"]');
    expect(errorText).toContain('email or password incorrect');

    await context.close()
});

test('When clicking home, redirects to main page', async ({ browser }) => {

    const context = await browser.newContext()
    const page = await context.newPage()
    // Navigate to the login page
    await page.goto('http://localhost:9000/gomoku/login');

    // Click the home link
    const homeLink = page.locator('.home-redirect');
    await homeLink.click();

    // Assert that the page redirected to the home page
    const newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/');

    await context.close()
});

test('When clicking signup, redirects to signup page', async ({ browser }) => {
    const context = await browser.newContext()
    const page = await context.newPage()
    // Navigate to the login page
    await page.goto('http://localhost:9000/gomoku/login');

    // Click the home link
    const homeLink = page.locator('#sign-up-redirect');
    await homeLink.click();

    // Assert that the page redirected to the home page
    const newUrl = page.url();
    expect(newUrl).toBe('http://localhost:9000/gomoku/register');

    await context.close()
});
