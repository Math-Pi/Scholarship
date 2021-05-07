import axios from 'axios'

export function get(url, params, config = {}) {
  return new Promise((resolve, reject) => {
    axios
      .get(url, { params: params, ...config })
      .then(res => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err.data);
      });
  });
}
export function post(url, datas,params, config = {}) {
    return new Promise((resolve, reject) => {
      axios
        .post(url,datas, { params: params, ...config })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err.data);
        });
    });
}
export function insert(url, datas, params, config = {}) {
  return new Promise((resolve, reject) => {
    axios
      .post(url, datas, { params: params, ...config })
      .then(res => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err.data);
      });
  });
}
export function remove(url, datas, params, config = {}) {
  return new Promise((resolve, reject) => {
    axios
      .post(url, datas, { params: params, ...config })
      .then(res => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err.data);
      });
  });
}