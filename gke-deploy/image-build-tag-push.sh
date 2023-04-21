docker-compose build

images=( $(docker image ls --format '{{.Repository}}') )

for image in "${images[@]}"
do
    if  [[ $image == rentler_* ]]
    then
      echo $image
      docker tag $image gcr.io/rentler-370619/$image
      docker push gcr.io/rentler-370619/$image
    fi
done