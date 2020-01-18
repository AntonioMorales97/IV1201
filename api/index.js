import config from 'dotenv';
import express from 'express';
import bodyParser from 'body-parser';
import applicantRoutes from './server/routes/ApplicantRoutes';

config.config();

const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use('/api/applicants', applicantRoutes);

// when a random route is inputed
app.get('*', (req, res) =>
  res.status(200).send({
    message: 'Welcome to this API.'
  })
);

// Serve static assets if in production
if (process.env.NODE_ENV === 'production') {
  // Set static folder
  app.use(express.static('client/build'));

  app.get('*', (req, res) => {
    res.sendFile(path.resolve(__dirname, 'client', 'build', 'index.html'));
  });
}

const port = process.env.PORT || 5000;

app.listen(port, () => {
  console.log(`Server is running on PORT ${port}`);
});
export default app;
