/* main.js — shared helpers */
const API_BASE = ''; // Set to your backend base URL, e.g. 'http://localhost:8080'

/* token helpers */
function saveToken(t) { localStorage.setItem('adduha_token', t); }

function getToken() { return localStorage.getItem('adduha_token'); }

function clearToken() { localStorage.removeItem('adduha_token'); }

/* fetch wrapper */
async function apiFetch(path, opts = {}) {
    const headers = opts.headers || {};
    headers['Content-Type'] = 'application/json';
    const token = getToken();
    if (token) headers['Authorization'] = 'Bearer ' + token;
    opts.headers = headers;
    const res = await fetch((API_BASE || '') + path, opts);
    if (!res.ok) {
        const text = await res.text().catch(() => null);
        throw new Error(text || 'Network response not ok: ' + res.status);
    }
    return res.json().catch(() => null);
}