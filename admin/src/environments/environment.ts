// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  API_BASE_URL: "http://localhost:9000/idle/api/",
  AUTH_URL: "http://localhost:8180/auth",
  WS_CHAT_BASE_URL: "ws://localhost:8083/ws",
  WS_MAIL_BASE_URL: "ws://localhost:8084/ws",
  WS_PLAYER_BASE_URL: "ws://localhost:8085/ws",
  AUTH_REALM: "idlerealm",
  AUTH_CLIENT: "idlegame",
};