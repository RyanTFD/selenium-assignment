# Selenium Assignment

For the full assignment description, task list, and deadlines, see the course materials.

Fill in `points.yml` to track your progress. The CI will calculate your grade on every push.

Start the container.
``` 
docker compose up
```
In another console enter into the container:
```
docker exec -it -u selenium docker-sandbox-ubuntu bash
```
### Step 3:
```cd test```

### Step 4:
```gradle clean && gradle test```