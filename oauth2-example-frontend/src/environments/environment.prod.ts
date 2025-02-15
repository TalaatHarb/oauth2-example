declare global {
  interface Window {
    _env_: any
  }
}

if (!window._env_) {
  window._env_ = {};
}

const API_URL = window._env_.API_URL ? window._env_.API_URL : 'http://localhost:8080';
const ISSUER_URL = window._env_.ISSUER_URL ? window._env_.ISSUER_URL : 'http://localhost:8180/realms/sample-realm';

export const environment = {
  apiUrl: API_URL,
  defaultPageSize: 10,
  defaultSort: 'updateDate,desc',
  issuerUrl: ISSUER_URL,
  production: true,
};
