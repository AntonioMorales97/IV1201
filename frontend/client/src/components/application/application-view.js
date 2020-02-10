import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Button, ListGroup, ListGroupItem } from 'reactstrap';
import { useTranslation } from 'react-i18next';

import './application.css';

const ApplicationView = props => {
  const { t } = useTranslation();
  const {
    id,
    status,
    person,
    competenceProfile,
    availability
  } = props.application;

  const { firstName, lastName, email, ssn } = person;

  const { acceptApplication, rejectApplication, unhandleApplication } = props;

  const badgeStatus = () => {
    if (status.name === 'accepted') {
      return 'badge-success';
    } else if (status.name === 'rejected') {
      return 'badge-danger';
    } else {
      return 'badge-secondary';
    }
  };

  return (
    <Container>
      <div className='application'>
        <div className='back'>
          <Link to='/applications'>
            <i className='fas fa-angle-double-left'></i>{' '}
            <span className='back-text'>
              {t('application.back_to_applications')}
            </span>
          </Link>
        </div>
        <h1>
          {t('application.application')} #{id}
        </h1>
        <ListGroup className='list-group-horizontal-md'>
          <ListGroupItem>
            <span className='bold'>{t('application.status')}: </span>
            <span className={'badge ' + badgeStatus()}>
              {t(`application.${status.name}`)}
            </span>
          </ListGroupItem>
        </ListGroup>
        <div className='personal-info mt-3'>
          <h2>{t('application.personal_info')}</h2>
          <ListGroup className='list-group-horizontal-lg'>
            <ListGroupItem>
              <span className='bold'>{t('personal.first_name')}: </span>
              {firstName}
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('personal.last_name')}: </span>
              {lastName}
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('personal.ssn')}: </span>
              {ssn}
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('personal.email')}: </span>
              {email}
            </ListGroupItem>
          </ListGroup>
        </div>
        <div className='experience mt-2'>
          <h2>{t('application.experience')}</h2>
          <ListGroup className='list-group list-group-horizontal-lg'>
            {competenceProfile.length > 0 ? (
              competenceProfile.map(experience => (
                <ListGroupItem key={experience.id}>
                  <span className='bold'>
                    {t(`application.competence.${experience.competence.name}`)}
                  </span>
                </ListGroupItem>
              ))
            ) : (
              <h4>{t('application.no_experiences')}</h4>
            )}
          </ListGroup>
        </div>
        <div className='availibility mt-2'>
          <h2>{t('application.availability')}</h2>
          <ListGroup className='list-group list-group-horizontal-lg'>
            {availability.length > 0 ? (
              availability.map(avail => (
                <ListGroupItem key={avail.id}>
                  <span className='bold'>{t('application.fromDate')} </span>{' '}
                  {avail.fromDate}{' '}
                  <span className='bold'>{t('application.toDate')} </span>{' '}
                  {avail.toDate}
                </ListGroupItem>
              ))
            ) : (
              <h4>{t('application.no_availability')}</h4>
            )}
          </ListGroup>
        </div>
        <div className='actions mt-3'>
          <Button className='btn-success mr-1' onClick={acceptApplication}>
            {t('application.accept')}
          </Button>
          <Button className='btn-danger mr-1' onClick={rejectApplication}>
            {t('application.reject')}
          </Button>
          <Button className='secondary' onClick={unhandleApplication}>
            {t('application.unhandle')}
          </Button>
        </div>
      </div>
    </Container>
  );
};

ApplicationView.propTypes = {};

export default ApplicationView;
