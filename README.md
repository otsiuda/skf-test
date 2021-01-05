- To launch whole solution just execute two commands

`mvn clean package` \
`docker-compose up -d`

- Available REST endpoints
    - `/publish?content={message}`
    - `/getLast`
    - `getByTime?start={start_date_time}&end={end_date_time}` 
    - _NOTE:_ start_date_time and end_date_time variables should have the following format
    `YYYY-MM-DDThh:mm:ss`, example `2021-01-05T11:00:00` 

