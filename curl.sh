#!/bin/bash
URL="http://localhost:8080/api/application"

# malformed requests
echo -e "\n\n *** Empty -> got 400"
curl -i -X POST -d '{}' -H "Content-Type: application/json" "${URL}"

echo -e "\n\n *** Partial -> got 400"
curl -i -X POST -d '{"loanAmount": 10000}' -H "Content-Type: application/json" "${URL}"

echo -e "\n\n *** Not valid -> got 400"
curl -i -X POST -d '{"loanAmount": 10, "loanTerm": 50, "applicantIncome": 50, "creditLoad": 0, "creditScore": 100}' -H "Content-Type: application/json" "${URL}"

# missing application
echo -e "\n\n *** nonexistent application -> got 404 ***\n"
curl -i "${URL}/123456"

# valid requests
echo -e "\n\n *** Add the first application"
curl -i -X POST -d '{"loanAmount": 1000000, "loanTerm": 12, "applicantIncome": 155000, "creditLoad": 5000, "creditScore": 300}' -H "Content-Type: application/json" "${URL}"

echo -e "\n\n *** Get previously created application ***\n"
curl -i "${URL}/1"

echo -e "\n\n *** Add the second application"
curl -i -X POST -d '{"loanAmount": 600000, "loanTerm": 12, "applicantIncome": 155000, "creditLoad": 1000, "creditScore": 500}' -H "Content-Type: application/json" "${URL}"

echo -e "\n\n *** Get previously created application ***\n"
curl -i "${URL}/2"

sleep 1

echo -e "\n\n *** Get updated status of the first application ***\n"
curl -i "${URL}/1/status"

echo -e "\n\n *** Get updated status of the second application ***\n"
curl -i "${URL}/2/status"
