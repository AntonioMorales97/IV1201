import chai from 'chai';
import chaiHttp from 'chai-http';
import 'chai/register-should';
import app from '../index';

chai.use(chaiHttp);
const { expect } = chai;

describe('Testing Applicants endpoints:', () => {
  it('It should create an applicant', done => {
    const applicant = {
      name: 'Dummy'
    };

    chai
      .request(app)
      .post('/api/applicants/')
      .set('Accept', 'application/json')
      .send(applicant)
      .end((err, res) => {
        expect(res.status).to.equal(201);
        expect(res.body.data).to.include({
          id: 1,
          name: applicant.name
        });
        done();
      });
  });

  it('It should get all books', done => {
    chai
      .request(app)
      .get('/api/applicants/')
      .accept('Accept', 'application/json')
      .end((err, res) => {
        expect(res.status).to.equal(200);
        res.body.data[0].should.have.property('id');
        res.body.data[0].should.have.property('name');
        done();
      });
  });
});
