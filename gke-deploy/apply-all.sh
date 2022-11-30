docker-compose build

images=( $(docker image ls --format '{{.Repository}}') )

for image in "${images[@]}"
do
    if  [[ $image == rentler_* ]]
    then
      echo $image
      docker tag $image gcr.io/decoded-petal-342615/$image
      docker push gcr.io/decoded-petal-342615/$image
    fi
done