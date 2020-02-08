import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Button, ListGroup, ListGroupItem } from 'reactstrap';
import { useTranslation } from 'react-i18next';

import './application.css';

const ApplicationView = props => {
  const { t } = useTranslation();
  const {
    id,
    firstName,
    lastName,
    date_of_birth,
    email,
    status,
    experiences,
    availabilities
  } = props.application;

  const { acceptApplication, rejectApplication, unhandleApplication } = props;

  const badgeStatus = () => {
    if (status === 'accepted') {
      return 'badge-success';
    } else if (status === 'rejected') {
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
            <span className='back-text'>{t('back_to_applications')}</span>
          </Link>
        </div>
        <h1>
          {t('application')} #{id}
        </h1>
        <ListGroup className='list-group-horizontal-md'>
          <ListGroupItem>
            <span className='bold'>{t('status')}: </span>
            <span className={'badge ' + badgeStatus()}>{t(status)}</span>
          </ListGroupItem>
        </ListGroup>
        <div className='personal-info mt-3'>
          <h2>{t('personal_info')}</h2>
          <ListGroup className='list-group-horizontal-lg'>
            <ListGroupItem>
              <span className='bold'>{t('first_name')}: </span>
              {firstName}
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('last_name')}: </span>
              {lastName}
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('birth_date')}: </span>
              {date_of_birth}
            </ListGroupItem>
            <ListGroupItem>
              <span className='bold'>{t('email')}: </span>
              {email}
            </ListGroupItem>
          </ListGroup>
        </div>
        <div className='experience mt-2'>
          <h2>{t('experience')}</h2>
          <ListGroup className='list-group list-group-horizontal-lg'>
            {experiences.length > 0 ? (
              experiences.map(experience => (
                <ListGroupItem key={experience.id}>
                  <span className='bold'>{t(experience.name)}</span>
                </ListGroupItem>
              ))
            ) : (
              <h4>{t('no_experiences')}</h4>
            )}
          </ListGroup>
        </div>
        <div className='availibility mt-2'>
          <h2>{t('availability')}</h2>
          <ListGroup className='list-group list-group-horizontal-lg'>
            {availabilities.length > 0 ? (
              availabilities.map(availability => (
                <ListGroupItem key={availability.id}>
                  <span className='bold'>{t('from')} </span> {availability.from}{' '}
                  <span className='bold'>{t('to')} </span> {availability.to}
                </ListGroupItem>
              ))
            ) : (
              <h4>{t('no_availability')}</h4>
            )}
          </ListGroup>
        </div>
        <div className='actions mt-3'>
          <Button className='btn-success mr-1' onClick={acceptApplication}>
            {t('accept')}
          </Button>
          <Button className='btn-danger mr-1' onClick={rejectApplication}>
            {t('reject')}
          </Button>
          <Button className='secondary' onClick={unhandleApplication}>
            {t('unhandle')}
          </Button>
        </div>
      </div>
    </Container>
  );
};

ApplicationView.propTypes = {};

export default ApplicationView;
