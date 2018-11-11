# www.beatfix.live

Generated from [React-Static](https://github.com/nozzle/react-static) (basic template).

`package.json` has a list of scripts that can be run with `yarn xxx`.

Release procedure:
1. `rm -rf dist`
1. `yarn build`
1. Delete entire contents of `www.beatfix.live` bucket
1. `aws s3 cp dist s3://www.beatfix.live --recursive`
1. Mark all newly uploaded files/folders as public (AWS UI)
