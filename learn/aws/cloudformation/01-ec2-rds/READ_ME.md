A first attempt at CloudFormation templates from a youtube tutorial.

# Tutorial
* URL: https://www.youtube.com/watch?v=8J0g_xWUzV0
* Author: Chandra Shettigar 
   
# Commands
(Assuming a 'learn' profile is present)
```bash
aws cloudformation describe-stacks --profile learn                                                                                                                                                                                           
aws cloudformation create-stack --stack-name pwl-learn-stack-01 --profile learn --template-body file://$PWD/stack.yml 
```