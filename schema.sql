CREATE TABLE "notes" (
  "id" uuid PRIMARY KEY,
  "title" varchar UNIQUE,
  "body" text,
  "user_id" uuid,
  "created_at" timestamp,
  "is_deleted" boolean DEFAULT false,
  "deleted_at" timestamp
);

CREATE TABLE "folders" (
  "id" uuid PRIMARY KEY,
  "title" varchar UNIQUE,
  "notes" uuid
);

CREATE TABLE "users" (
  "id" uuid PRIMARY KEY,
  "name" varchar,
  "email" varchar UNIQUE,
  "notes" uuid,
  "folders" uuid,
  "pomodoro_stats" uuid,
  "pomodoro_sessions" uuid
);

CREATE TABLE "pomodoro_stats" (
  "id" uuid PRIMARY KEY,
  "average" time
);

CREATE TABLE "pomodoro_sessions" (
  "id" uuid PRIMARY KEY,
  "duration" time,
  "created_at" timestamp,
  "finished_at" timestamp
);

ALTER TABLE "notes" ADD FOREIGN KEY ("id") REFERENCES "folders" ("notes");

ALTER TABLE "notes" ADD FOREIGN KEY ("id") REFERENCES "users" ("notes");

ALTER TABLE "folders" ADD FOREIGN KEY ("id") REFERENCES "users" ("folders");

ALTER TABLE "users" ADD FOREIGN KEY ("pomodoro_stats") REFERENCES "pomodoro_stats" ("id");

ALTER TABLE "pomodoro_sessions" ADD FOREIGN KEY ("id") REFERENCES "users" ("pomodoro_sessions");
