// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  WS_BASE_URL: "ws://localhost:8180/idle/websocket",
  API_BASE_URL: "http://localhost:9000/idle/api/",
  AUTH_URL: "http://localhost:8180/auth",
  AUTH_REALM: "idlerealm",
  AUTH_CLIENT: "idlegame",
};